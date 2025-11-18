package org.example

class LottoController() {
    private val service: LottoService = LottoService();
    private val outputView: LottoOutputView = LottoOutputView();
    private val inputView: LottoInputView = LottoInputView();

    fun runLotto() {
        outputView.welcomeMessageOutput();

        var purchaseAmount = readPurchaseAmount();
        var purchaseType = readPurchaseType();

        var issuedLottoNumbers: List<Lotto>? = null;

        outputView.printIssueTitle(purchaseType);
        if (purchaseType == 1) {
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

    private fun readPurchaseAmount(): Int {
        while (true) {
            val result = kotlin.runCatching {
                val purchaseAmount = inputView.inputPurchaseAmount();
                service.validatePurchaseAmount(purchaseAmount);
                purchaseAmount
            }.onFailure { e ->
                ExceptionHandler.handleException(e)
            }
            result.getOrNull()?.let { return it }
        }
    }

    private fun readPurchaseType(): Int {
        while (true) {
            val result = runCatching {
                val purchaseType = inputView.inputPurchaseType()
                service.validatePurchaseType(purchaseType)
                purchaseType
            }.onFailure { e ->
                ExceptionHandler.handleException(e)
            }
            result.getOrNull()?.let { return it }
        }
    }

    fun manualIssue(purchaseAmount: Int): List<Lotto> {
        var issuedLottoNumbers = mutableListOf<Lotto>();
        var i = 1;
        while (i <= purchaseAmount / 5000) {
            val result = runCatching {
                val inputLottoNumbers = inputView.inputManualLottoNumbers(i)
                val parsed = service.parseInput(inputLottoNumbers)
                issuedLottoNumbers.add(parsed)
                i++
            }.onFailure { e ->
                ExceptionHandler.handleException(e)
            }
        }
        return issuedLottoNumbers
    }

    fun autoIssue(purchaseAmount: Int): List<Lotto> {
        var issuedLottoNumbers = mutableListOf<Lotto>();
        for (i: Int in 1..purchaseAmount / 5000) {
            issuedLottoNumbers.add(service.generateLottoNumbers());
        }
        return issuedLottoNumbers;
    }
}