# DsMVP
## 前言
刚开始进行 Android 开发的时候，对架构并没有很好的概念，于是所有的功能和操作都集中在 Activity 和 Fragment 中，在项目小的时候其实也并没有觉得十分不妥；然而当项目像滚雪球一样越滚越大，Activity 和 Fragment 会变得无比的臃肿和凌乱，此时最痛苦的事情莫过于在茫茫码海中找寻 Bug 或增减功能。

所以，对于项目中后期，一个好的架构是非常有必要的，在网上寻找解决方案，大部分的资料都会最终指向 Google 提供的 [todo‑mvp](https://github.com/googlesamples/android-architecture)，该示例提供了完整的解决方案，并有很多大神以此为基础，集成了众多第三方组件而推出了开发框架，我也开始尝试使用这些框架，确实极大的提高了代码的可维护性，但由于不同项目的特殊性，单一的框架要么不能满足需求、要么显得过于庞大，因此，在这里实现一个简化的 MVP 框架，尽量只包含核心的模块，开发时导入项目后再根据实际的需求进行改进扩展。

## 设计思想
Google官方MVP示例是将 Activity 作为 V，单独创建 Presenter 将原来在 Activity 中的逻辑功能剥离出来；但由于 Android 中对 Context 依赖性很强，而 Activity 正是最常用的 Context，所以 Activity 更适合做 P，而将 View 层使用一个类来封装所有 View 控件的修改和事件监听，View 与 Presenter 通讯通过接口隔离。

| View        | Presenter    |  Model  |
| ----- | -----  | ---- |
| ViewContent	|Activity / Fragment	|OkHttp/sqlite...|
| 实现所有View逻辑，隔离用户与P |业务逻辑，隔离V和M，通过接口与两者交互 |数据交互|

> 该思想的实现主要参考自 [Android MVP 不一样的实现方案](https://github.com/Yeamy/MVPDemo/blob/master/README.md)

## 实现
### View
#### [ContentView](https://github.com/wangenyong/dsmvp/blob/master/mvp/src/main/java/com/wangenyong/mvp/view/ContentView.java)
View的基类，处理所有有关View的操作，实际使用时，只需继承 ContentView 来实现不同的视图页面。
```
@BindLayout(R.layout.activity_main)
public class MainView extends Conetent {
  @BindId(R.id.bottom_bar)
  ...
}
```
这里的注解会在 ContentView 中进行处理，实现视图以及点击事件的绑定，然后在 Activity 或者 Fragment 中进行初始化即可。
#### [MainActivity](https://github.com/wangenyong/dsmvp/blob/master/demo/src/main/java/com/wangenyong/dsmvp/MainActivity.java)
```
public class MainActivity extends BaseActivity {
  private MainView contentView = new MainView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(contentView.createView(this, savedInstanceState));
    }
    ...
}
```
#### [GankFragment](https://github.com/wangenyong/dsmvp/blob/master/demo/src/main/java/com/wangenyong/dsmvp/presentation/GankFragment.java)
```
public class GankFragment extends BaseFragment implements GankFragmentView.ActionImpl {
    private GankFragmentView contentView = new GankFragmentView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = contentView.createView(inflater, savedInstanceState);
        contentView.setActionImpl(this);
        return view;
    }
    ...
}
```
### P 和 V 的通信
Activity 和 Fragment 持有 ContentView，可以直接调用 View 中的方法来显示内容；
