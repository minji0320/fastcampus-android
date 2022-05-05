package fastcampus.aop.part5.chapter06.data.repository

import fastcampus.aop.part5.chapter06.data.entity.ShippingCompany

interface ShippingCompanyRepository {

    suspend fun getShippingCompanies(): List<ShippingCompany>
}