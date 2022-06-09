package fastcampus.aop.part6.chapter01.viewmodel.restaurant

import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.part6.chapter01.data.repository.restaurant.RestaurantRepository
import fastcampus.aop.part6.chapter01.model.restaurant.RestaurantModel
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.RestaurantCategory
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.RestaurantListViewModel
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.RestaurantOrder
import fastcampus.aop.part6.chapter01.viewmodel.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

@ExperimentalCoroutinesApi
internal class RestaurantListViewModelTest : ViewModelTest() {

    /**
     * [RestaurantCategory]
     * [LocationLatLngEntity]
     */

    private var restaurantCategory = RestaurantCategory.ALL

    private val locationLatLngEntity: LocationLatLngEntity = LocationLatLngEntity(0.0, 0.0)

    private val restaurantRepository by inject<RestaurantRepository>()

    private val restaurantListViewModel by inject<RestaurantListViewModel> {
        parametersOf(
            restaurantCategory,
            locationLatLngEntity
        )
    }

    @Test
    fun `test load restaurant list category ALL`() = runTest {
        val testObservable = restaurantListViewModel.restaurantListLiveData.test()

        restaurantListViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                restaurantRepository.getList(restaurantCategory, locationLatLngEntity).map {
                    RestaurantModel(
                        id = it.id,
                        restaurantInfoId = it.restaurantInfoId,
                        restaurantCategory = it.restaurantCategory,
                        restaurantTitle = it.restaurantTitle,
                        restaurantImageUrl = it.restaurantImageUrl,
                        grade = it.grade,
                        reviewCount = it.reviewCount,
                        deliveryTimeRange = it.deliveryTimeRange,
                        deliveryTipRange = it.deliveryTipRange,
                        restaurantTelNumber = it.restaurantTelNumber
                    )
                }
            )
        )
    }

    @Test
    fun `test load restaurant list category Excepted`() = runTest {
        restaurantCategory = RestaurantCategory.CAFE_DESSERT

        val testObservable = restaurantListViewModel.restaurantListLiveData.test()

        restaurantListViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                listOf()
            )
        )
    }

    @Test
    fun `test load restaurant list order by fast delivery time`() = runTest {
        val testObservable = restaurantListViewModel.restaurantListLiveData.test()

        restaurantListViewModel.setRestaurantOrder(RestaurantOrder.FAST_DELIVERY)

        testObservable.assertValueSequence(
            listOf(
                restaurantRepository.getList(restaurantCategory, locationLatLngEntity)
                    .sortedBy { it.deliveryTimeRange.first }
                    .map {
                        RestaurantModel(
                            id = it.id,
                            restaurantInfoId = it.restaurantInfoId,
                            restaurantCategory = it.restaurantCategory,
                            restaurantTitle = it.restaurantTitle,
                            restaurantImageUrl = it.restaurantImageUrl,
                            grade = it.grade,
                            reviewCount = it.reviewCount,
                            deliveryTimeRange = it.deliveryTimeRange,
                            deliveryTipRange = it.deliveryTipRange,
                            restaurantTelNumber = it.restaurantTelNumber
                        )
                    }
            )
        )
    }
}