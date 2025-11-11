package org.example

class LottoController() {
    private val outputView: LottoOutputView = LottoOutputView();
    private val inputView: LottoInputView = LottoInputView();

    fun runLotto() {
        outputView.welcomeMessageOutput();
        var purchaseAmount = inputView.inputPurchaseAmount();
        var purchaseType = inputView.inputPurchaseType();
    }
}