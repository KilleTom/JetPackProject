# Navigation 用法详解

`Navigation`主要运用于管理`fragment`之间的跳转，通过可视化的操作，使得开发者能够更好的操作`fragment`之间的跳转的关系，`navigation`将`fragment`好处在于：

1. 处理`fragment`之间的转场动画
2. 在一系列有序的`fragment`，让它们的顺序变得更加可靠‘
3. 将一系列有序的`fragment`，产生相同的堆栈。



## Navigation 使用配置

1. 项目构建配置

   ```groovy
   // Java language implementation
   implementation "androidx.navigation:navigation-fragment:2.3.0"
   implementation "androidx.navigation:navigation-ui:2.3.0"
   
   // Kotlin
   implementation "androidx.navigation:navigation-fragment-ktx:2.3.0"
   implementation "androidx.navigation:navigation-ui-ktx:2.3.0"
   
   // Feature module Support
   implementation "androidx.navigation:navigation-dynamic-features-fragment:2.3.0"
   
   // Testing Navigation
   androidTestImplementation "androidx.navigation:navigation-testing:2.3.0"
   ```
   
2. 相关配置的解释

   | 关键攻略          | 解析                                                         |
| ----------------- | ------------------------------------------------------------ |
   | NavHostFragment   | jetpack 实现好的导航fragment 其内部以封装好 navigation 跳转处理逻辑 |
   | navGraph          | 代表需要引用哪一个 navigation 文件                           |
   | navigation 文件夹 | 负责存放 navigation配置文件                                  |
   | startDestination  | 最开始的展示的 fragment 通过配置文件内的id进行索引           |
   | action            | 表示当前fragment 一些操作动作 例如 转场动画、下一个需要展示的 fragment |
   
   `Navigation`最为关键的一步在于，在`Android`项目工程在`navigation`文件目录中创建相应的`navigation`文件，来设置相应一系列有序的`fragment`相互间的关系。
   
   
## Navigation 基本使用

在上一节中我们简单的了解Navigation的基本配置，以下内容将配合上一节的概念简单的去使用navigation。

1. 以下以`lib_navigation_demo_nav`示例代码进行演示如何配置好一个简单的导航文件

   ```xml
      <?xml version="1.0" encoding="utf-8"?>
      <navigation xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:id="@+id/lib_navigation_demo_nav"
       app:startDestination="@id/fragmentA"
       tools:ignore="UnusedNavigation">
      
       <fragment
           android:id="@+id/fragmentA"
           android:name="com.killeTom.navigation.fragment.NavDemoFragmentA"
           android:label="fragmentA"
           tools:layout="@layout/nav_demo_fragment_a">
      
           <action
               android:id="@+id/action_fragmentA_to_fragmentB"
               app:destination="@id/fragmentB"
               app:exitAnim="@android:anim/slide_out_right" />
       </fragment>
      
       <fragment
           android:id="@+id/fragmentB"
           android:name="com.killeTom.navigation.fragment.NavDemoFragmentB"
           android:label="fragmentB"
           tools:layout="@layout/nav_demo_fragment_b">
      
           <action
               android:id="@+id/action_fragmentB_to_fragmentC"
               app:destination="@id/fragmentC"
               app:exitAnim="@android:anim/slide_out_right" />
       </fragment>
      
       <fragment
           android:id="@+id/fragmentC"
           android:name="com.killeTom.navigation.fragment.NavDemoFragmentC"
           android:label="fragmentC"
           tools:layout="@layout/nav_demo_fragment_c">
           <action
               android:id="@+id/action_fragmentC_to_fragmentA"
               app:destination="@id/fragmentA"
               app:exitAnim="@android:anim/slide_out_right" />
       </fragment>
      </navigation>
   ```
      其视图预览如下图：
      ![](D:\StudyProject\Android\JetPack\lib_navigation\src\main\assets\QQ截图20201002124556.png)

2. 在`Activity`对应的xml文件引用`navigation`

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto"
       xmlns:tools="http://schemas.android.com/tools"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       tools:context=".NavigationDemoActivity">
       
       <fragment
           android:id="@+id/nav_demo"
           android:name="androidx.navigation.fragment.NavHostFragment"
           android:layout_width="0dp"
           android:layout_height="0dp"
           app:navGraph="@navigation/lib_navigation_demo_nav"
           app:defaultNavHost="true"
           app:layout_constraintStart_toStartOf="parent"
           app:layout_constraintEnd_toEndOf="parent"
           app:layout_constraintTop_toTopOf="parent"
           app:layout_constraintBottom_toBottomOf="parent"/>
   
   </androidx.constraintlayout.widget.ConstraintLayout>
   ```
   
   通过这样的引用就实现出 A -> B -> C -> A 这样一个fragment切换显示的顺序。

3. fragment值传递的实现方式

4. 常见的一些错误信息

   

## Navigation 原理解析



## 总结


```

```