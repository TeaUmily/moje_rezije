package hr.tumiljanovic.mojerezije.data.database.model

import hr.tumiljanovic.mojerezije.common.constants.IN_APP_DATE_FORMAT
import hr.tumiljanovic.mojerezije.common.utils.DateUtils
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.util.EntityMapper
import java.util.*

class BillDbMapper: EntityMapper<BillDbEntity, Bill> {

    override fun mapFromEntity(entity: BillDbEntity): Bill {
        return Bill(
            id = entity.id,
            note = entity.note,
            amount = entity.amount,
            dueDate = DateUtils.convertStringToDateByFormat(IN_APP_DATE_FORMAT, entity.dueDate) ?: Date(),
            utilityTitle = entity.utilityTitle,
            isPaid = entity.isPaid
        )
    }

    override fun mapToEntity(domainModel: Bill): BillDbEntity {
        return BillDbEntity(
            id = domainModel.id,
            note = domainModel.note,
            amount = domainModel.amount,
            dueDate = DateUtils.dateToFormat(IN_APP_DATE_FORMAT, domainModel.dueDate),
            utilityTitle = domainModel.utilityTitle,
            isPaid = domainModel.isPaid
        )
    }
}