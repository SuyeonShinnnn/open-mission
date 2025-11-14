package org.example

class LottoController() {
    private val service: LottoService = LottoService();
    private val outputView: LottoOutputView = LottoOutputView();
    private val inputView: LottoInputView = LottoInputView();

    fun runLotto() {
        outputView.welcomeMessageOutput();
        var purchaseAmount = inputView.inputPurchaseAmount();
        service.validatePurchaseAmount(purchaseAmount);

        var purchaseType = inputView.inputPurchaseType();
        var issuedLottoNumbers: List<Lotto>? = null;

        if (purchaseType == 1) {
            println("\n** 로또 번호를 수동으로 발급합니다 **");
            println("번호를 입력해 주세요. (예: 1, 2, 3, 4, 5, 6)");
            issuedLottoNumbers = manualIssue(purchaseAmount);
        } else if (purchaseType == 2) {
            issuedLottoNumbers = autoIssue(purchaseAmount);
        }
        outputView.outputIssuedLottoNumbers(issuedLottoNumbers);
        var winningNumber = service.generateLottoNumbers();
        var bonusNumber = service.generateBonusNumber(winningNumber);
        outputView.outputWinningNumber(winningNumber, bonusNumber);


        if (issuedLottoNumbers != null) {
            var result = service.matchNumbers(issuedLottoNumbers, winningNumber, bonusNumber);
            outputView.outputResult(result);
        }
    }

    fun manualIssue(purchaseAmount: Int): List<Lotto> {
        var issuedLottoNumbers = mutableListOf<Lotto>();
        var i = 1;
        while (i <= purchaseAmount / 5000) {
            try {
                var inputLottoNumbers = inputView.inputManualLottoNumbers(i);
                val parsedInputLottoNumbers = service.parseInput(inputLottoNumbers);
                issuedLottoNumbers.add(parsedInputLottoNumbers);
                i++;
            } catch (e: IllegalArgumentException) {
                println(e.message);
            }
        }
        return issuedLottoNumbers;
    }

    fun autoIssue(purchaseAmount: Int): List<Lotto> {
        var issuedLottoNumbers = mutableListOf<Lotto>();
        for (i: Int in 1..purchaseAmount / 5000) {
            issuedLottoNumbers.add(service.generateLottoNumbers());
        }
        return issuedLottoNumbers;
    }
}