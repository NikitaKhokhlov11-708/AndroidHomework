package com.example.androidhomework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.gms.wallet.*
import com.google.android.gms.wallet.WalletConstants.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var googlePaymentsClient: PaymentsClient
    val LOAD_PAYMENT_DATA_REQUEST_CODE = 228

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googlePaymentsClient = GooglePaymentUtils.createGoogleApiClientForPay(this)

        GooglePaymentUtils.isReadyToPay(googlePaymentsClient).addOnCompleteListener { task ->
            try {
                setGooglePayButtonAvailable(task.getResult(ApiException::class.java))
            } catch (exception: ApiException) {
                tvStatus.text = "Payment status: Error init"
                Log.d("isReadyToPay failed", exception.statusCode.toString())
            }
        }

        btnGooglePay.setOnClickListener {
            val request = GooglePaymentUtils.createPaymentDataRequest("750")
            AutoResolveHelper.resolveTask<PaymentData>(
                googlePaymentsClient.loadPaymentData(request),
                this,
                LOAD_PAYMENT_DATA_REQUEST_CODE
            )
        }
    }

    fun setGooglePayButtonAvailable(available: Boolean) {
        if (available) {
            tvStatus.text = "Payment status: Supported"
            btnGooglePay.visibility = View.VISIBLE
        } else {
            tvStatus.text = "Payment status: Not supported"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            LOAD_PAYMENT_DATA_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        if (data == null)
                            return

                        val paymentData = PaymentData.getFromIntent(data)
                    }
                    Activity.RESULT_CANCELED -> {
                    }
                    AutoResolveHelper.RESULT_ERROR -> {
                        if (data == null)
                            return

                        val status = AutoResolveHelper.getStatusFromIntent(data)
                        Log.e("GOOGLE PAY", "Load payment data has failed with status: $status")
                    }
                    else -> {
                    }
                }
            }
            else -> {
            }
        }
    }
}

object GooglePaymentUtils {
    private val SUPPORTED_METHODS = Arrays.asList(
        PAYMENT_METHOD_CARD,
        PAYMENT_METHOD_TOKENIZED_CARD
    )

    fun createGoogleApiClientForPay(context: Context): PaymentsClient =
        Wallet.getPaymentsClient(
            context,
            Wallet.WalletOptions.Builder()
                .setEnvironment(ENVIRONMENT_TEST)
                .setTheme(THEME_LIGHT)
                .build()
        )

    fun isReadyToPay(client: PaymentsClient): Task<Boolean> {
        val request = IsReadyToPayRequest.newBuilder()
        for (allowedMethod in SUPPORTED_METHODS) {
            request.addAllowedPaymentMethod(allowedMethod)
        }
        return client.isReadyToPay(request.build())
    }


    fun createPaymentDataRequest(price: String): PaymentDataRequest {
        val transaction = createTransaction(price)
        val request = generatePaymentRequest(transaction)
        return request
    }

    fun createTransaction(price: String): TransactionInfo {
        val CURRENCY_CODE = "RUB"
        return TransactionInfo.newBuilder()
            .setTotalPriceStatus(TOTAL_PRICE_STATUS_FINAL)
            .setTotalPrice(price)
            .setCurrencyCode(CURRENCY_CODE)
            .build()
    }

    private fun generatePaymentRequest(transactionInfo: TransactionInfo): PaymentDataRequest {
        val tokenParams = PaymentMethodTokenizationParameters
            .newBuilder()
            .setPaymentMethodTokenizationType(PAYMENT_METHOD_TOKENIZATION_TYPE_DIRECT)
            .addParameter("gateway", "yourGateway")
            .addParameter("gatewayMerchantId", "yourMerchantIdGivenFromYourGateway")
            .build()


        return PaymentDataRequest.newBuilder()
            .setPhoneNumberRequired(false)
            .setEmailRequired(true)
            .setShippingAddressRequired(true)
            .setTransactionInfo(transactionInfo)
            .addAllowedPaymentMethods(SUPPORTED_METHODS)
            .setCardRequirements(
                CardRequirements.newBuilder()
                    .addAllowedCardNetworks(
                        listOf(
                            CARD_NETWORK_AMEX,
                            CARD_NETWORK_JCB,
                            CARD_NETWORK_DISCOVER,
                            CARD_NETWORK_MASTERCARD,
                            CARD_NETWORK_VISA
                        )
                    )
                    .build()
            )
            .setPaymentMethodTokenizationParameters(tokenParams)
            .setUiRequired(true)
            .build()
    }

}
