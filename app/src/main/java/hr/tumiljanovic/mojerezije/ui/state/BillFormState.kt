package hr.tumiljanovic.mojerezije.ui.state


data class BillFormState(
    val id: String = "",
    val selectedUtility: String = "",
    val amount: String = "",
    val dueDate: String = "",
    val note: String = "",
    val isPaid: Boolean = false,
    val showAmountError: Boolean = false,
    val utilityList: List<String> = listOf(),
    val title: String = "",
    val actionButtonText: String = "",
    val showDeleteButton: Boolean = false,
    val popBackStack: Boolean = false,
    val showQrCodeScannerButton: Boolean = true
)