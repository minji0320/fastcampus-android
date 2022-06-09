package fastcampus.aop.part6.chapter01.viewmodel.order

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import fastcampus.aop.part6.chapter01.data.entity.OrderEntity
import fastcampus.aop.part6.chapter01.data.entity.RestaurantFoodEntity
import fastcampus.aop.part6.chapter01.data.repository.order.DefaultOrderRepository
import fastcampus.aop.part6.chapter01.data.repository.order.OrderRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.food.RestaurantFoodRepository
import fastcampus.aop.part6.chapter01.model.CellType
import fastcampus.aop.part6.chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.part6.chapter01.screen.order.OrderMenuListViewModel
import fastcampus.aop.part6.chapter01.screen.order.OrderMenuState
import fastcampus.aop.part6.chapter01.viewmodel.ViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
internal class OrderMenuListViewModelTest : ViewModelTest() {

    @Mock
    lateinit var firebaseAuth: FirebaseAuth

    @Mock
    lateinit var firebaseUser: FirebaseUser

    private val orderMenuListViewModel by inject<OrderMenuListViewModel> {
        parametersOf(firebaseAuth)
    }

    private val restaurantFoodRepository by inject<RestaurantFoodRepository>()

    private val orderRepository by inject<OrderRepository>()

    private val restaurantId = 0L

    private val restaurantTitle = "식당명"

    /**
     * 1. 장바구니에 메뉴를 담는다.
     * 2. 장바구니에 담은 메뉴를 리스트로 뿌려준다.
     * 3. 장바구니 목록에 있는 데이터를 갖고 주문을 한다.
     */

    @Test
    fun `insert food menus in basket`() = runTest {
        (0 until 10).forEach {
            restaurantFoodRepository.insertFoodMenuInBasket(
                RestaurantFoodEntity(
                    id = it.toString(),
                    title = "메뉴 $it",
                    description = "소개 $it",
                    price = it,
                    imageUrl = "",
                    restaurantId = restaurantId,
                    restaurantTitle = restaurantTitle
                )
            )
        }
        assert(restaurantFoodRepository.getAllFoodMenuListInBasket().size == 10)
    }

    @Test
    fun `test load order menu list`() = runTest {
        val testObserver = orderMenuListViewModel.orderMenuStateLiveData.test()
        orderMenuListViewModel.fetchData()

        testObserver.assertValueSequence(
            listOf(
                OrderMenuState.Uninitialized,
                OrderMenuState.Loading,
                OrderMenuState.Success(
                    restaurantFoodRepository.getAllFoodMenuListInBasket().map {
                        FoodModel(
                            id = it.hashCode().toLong(),
                            type = CellType.ORDER_FOOD_CELL,
                            title = it.title,
                            description = it.description,
                            price = it.price,
                            imageUrl = it.imageUrl,
                            restaurantId = it.restaurantId,
                            foodId = it.id,
                            restaurantTitle = it.restaurantTitle
                        )
                    }
                )
            )
        )
    }

    @Test
    fun `test do order menu list`() = runTest {
        `insert food menus in basket`()

        val userId = "aaa"
        Mockito.`when`(firebaseAuth.currentUser).then { firebaseUser }
        Mockito.`when`(firebaseUser.uid).then { userId }

        val testObserver = orderMenuListViewModel.orderMenuStateLiveData.test()

        orderMenuListViewModel.fetchData()

        val menuListInBasket =
            restaurantFoodRepository.getAllFoodMenuListInBasket().map { it.copy() }

        val menuListInBasketModelList = menuListInBasket.map {
            FoodModel(
                id = it.hashCode().toLong(),
                type = CellType.ORDER_FOOD_CELL,
                title = it.title,
                description = it.description,
                price = it.price,
                imageUrl = it.imageUrl,
                restaurantId = it.restaurantId,
                foodId = it.id,
                restaurantTitle = it.restaurantTitle
            )
        }

        orderMenuListViewModel.orderMenu()

        testObserver.assertValueSequence(
            listOf(
                OrderMenuState.Uninitialized,
                OrderMenuState.Loading,
                OrderMenuState.Success(
                    menuListInBasketModelList
                ),
                OrderMenuState.Order
            )
        )

        assert(orderRepository.getAllOrderMenus(userId) is DefaultOrderRepository.Result.Success<*>)
        val result =
            (orderRepository.getAllOrderMenus(userId) as DefaultOrderRepository.Result.Success<*>).data

        assert(
            result?.equals(
                listOf(
                    OrderEntity(
                        id = 0.toString(),
                        userId = userId,
                        restaurantId = restaurantId,
                        foodMenuList = menuListInBasket,
                        restaurantTitle = restaurantTitle
                    )
                )
            ) ?: false
        )
    }
}