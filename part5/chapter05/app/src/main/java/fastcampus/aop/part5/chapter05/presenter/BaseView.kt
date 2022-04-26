package fastcampus.aop.part5.chapter05.presenter

interface BaseView<PresenterT : BasePresenter> {

    val presenter: PresenterT

}
