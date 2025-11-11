package org.example

import jdk.jfr.DataAmount

class LottoController() {
    private val service: LottoService = LottoService();
    private val outputView: LottoOutputView = LottoOutputView();
    private val inputView: LottoInputView = LottoInputView();

    fun runLotto() {
        outputView.welcomeMessageOutput();
        var purchaseAmount = inputView.inputPurchaseAmount();
        var purchaseType = inputView.inputPurchaseType();

        if (purchaseType == 1) {
            println("\n** 로또 번호를 수동으로 발급합니다 **");
            println("번호를 입력해 주세요. (예: 1, 2, 3, 4, 5, 6)");
            manualIssue(purchaseAmount);
        } else if (purchaseType == 2) {
            println("자동 발급")
        }
    }

    fun manualIssue(purchaseAmount: Int) {
        var issuedLottoNumbers = mutableListOf<Lotto>();
        for (i: Int in 1..purchaseAmount / 5000){
            var inputLottoNumbers = inputView.inputManualLottoNumbers(i);
            val parsedInputLottoNumbers = service.parseInput(inputLottoNumbers);

            issuedLottoNumbers.add(parsedInputLottoNumbers);
        }
    }
}