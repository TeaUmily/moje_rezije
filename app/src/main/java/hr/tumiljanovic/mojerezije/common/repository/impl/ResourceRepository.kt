package hr.tumiljanovic.mojerezije.common.repository.impl

import android.content.res.Resources
import hr.tumiljanovic.mojerezije.common.repository.ResourceRepository

class ResourceRepositoryImpl(
    private val resourcesProvider: Resources
): ResourceRepository {
    override fun getResources() = resourcesProvider
    override fun getStringArray(arrayId: Int): Array<String> = resourcesProvider.getStringArray(arrayId)
    override fun getString(stringId: Int) = resourcesProvider.getString(stringId)
}