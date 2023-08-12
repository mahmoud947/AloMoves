package com.example.alomoves.ui.base

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.data.Resource
import com.example.core.presentaion.dialogs.ErrorDialog
import com.example.core.presentaion.dialogs.LoadingDialog
import com.example.core.utils.UriPathHelper
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {
    abstract val viewModel: BaseViewModel
    lateinit var loadingDialog: LoadingDialog
    lateinit var errorDialog: ErrorDialog
    lateinit var uriPathHelper: UriPathHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = LoadingDialog(requireActivity())
        errorDialog = ErrorDialog(activity = requireActivity(), buttonTitle = "Retry")
        uriPathHelper = UriPathHelper()
    }

    override fun onStart() {
        super.onStart()

        errorDialog.onClick {
            errorDialog.dismissDialog()
        }


        viewModel.showErrorMessage.observe(this) {
            if (it != null) {
                loadingDialog.dismissDialog()
                errorDialog.updateMessage(it.message)
                errorDialog.updateButtonTitle(it.button)
                errorDialog.startDialog()
            }
        }

        viewModel.showLoading.observe(this) {
            if (it) {
                loadingDialog.startDialog()
            } else {
                loadingDialog.dismissDialog()
            }
        }
        viewModel.showToast.observe(this) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        viewModel.showSnackBar.observe(this) {
            Snackbar.make(this.requireView(), it, Snackbar.LENGTH_LONG).show()
        }
        viewModel.showSnackBarInt.observe(this) {
            Snackbar.make(this.requireView(), getString(it), Snackbar.LENGTH_LONG).show()
        }

        viewModel.navigationCommand.observe(this) { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(command.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.BackTo -> findNavController().popBackStack(
                    command.destinationId, false
                )
                is NavigationCommand.UnAuthorized<*> -> {
                    val intent = Intent(requireContext(), command.activity)
                    startActivity(intent)
                    requireActivity().finish()
                }
                is NavigationCommand.ToActivity<*> -> {
                    val intent = Intent(requireContext(), command.activity)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }
    }

    fun <T> dataStateHandler(
        resource: Resource<T>, onSuccess: (T) -> Unit
    ) {
        when (resource) {
            is Resource.Error -> {
                loadingDialog.dismissDialog()
                errorDialog.updateMessage(resource.exception?.message ?: "Un known Error")
                errorDialog.updateButtonTitle("ok")
                errorDialog.startDialog()
            }
            is Resource.Loading -> {
                loadingDialog.startDialog()
            }
            is Resource.ErrorWithMessage -> {
                loadingDialog.dismissDialog()
                errorDialog.updateMessage(resource.errorMessage)
                errorDialog.startDialog()

            }
            is Resource.Success -> {
                loadingDialog.dismissDialog()
                onSuccess(resource.data)
            }
        }
    }

    fun Context.hasPermissions( permissions: Array<String>): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
}
