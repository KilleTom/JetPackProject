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

   

3. 利用navigation对fragment进行跳转

   下面将以NavDemoFragmentA跳转到NavDemoFragmentB为示例

   ```kotlin
   class NavDemoFragmentA : Fragment() {
   
       override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
       ): View? {
           val view = inflater.inflate(R.layout.nav_demo_fragment_a, container, false)
   
           view.nav_action.setOnClickListener {
   			//通过利用findNavController获取导航控制器，然后指向配置文件中的actionId进行控制跳转
               findNavController().navigate(R.id.action_fragmentA_to_fragmentB)
           }
   
           return view
       }
   
   
   }
   ```

   

4. 利用navigation对fragment进行值传递跳转

   navigation值传递分为`Bundle`、`safeargs`两种以下针对这两种方式分别讲解

   1. 利用`Bundle`进行值传递

      Bundle传值在于构建出一个Bundle对象，在里面存放数据，然后通过调用navigate时，将Bundle传入

      譬如：NavDemoFragmentA跳转到NavDemoFragmentB时将一个 hello 字符串传递

      ```kotlin
      class NavDemoFragmentA : Fragment() {
      
          override fun onCreateView(
              inflater: LayoutInflater,
              container: ViewGroup?,
              savedInstanceState: Bundle?
          ): View? {
              val view = inflater.inflate(R.layout.nav_demo_fragment_a, container, false)
      
              view.nav_action.setOnClickListener {
      			
                  var bundle: Bundle = bundleOf("value" to "hello")
                  
                  findNavController()
                  .navigate(R.id.action_fragmentA_to_fragmentB,bundle)
                  
              }
      
              return view
          }
      
      }
      
      
      class NavDemoFragmentB :Fragment() {
      
          override fun onCreateView(
              inflater: LayoutInflater,
              container: ViewGroup?,
              savedInstanceState: Bundle?
          ): View? {
              val view =  inflater.inflate(R.layout.nav_demo_fragment_b, container, false)
      
              //取值
              var message = arguments?.getString("value")?: view.nav_action.text
              
              view.nav_action.text = message
      
              return view
          }
      }
      ```

       

   2. 利用`safeargs`进行类型安全值传递

      利用`safeargs`首先得在gralde文件中配置：

      ```groovy
      buildscript {
          repositories {
              google()
          }
          dependencies {
              def nav_version = "2.3.0"
              classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
          }
      }
      ```

      然后在需要使用`navigation`的模块的gradle文件中添加如下配置

      ```groovy
      apply plugin: "androidx.navigation.safeargs"
      apply plugin: "androidx.navigation.safeargs.kotlin"
      ```

      到此`safeargs`环境配置则已经完成紧接着我们以 NavDemoFragmentC 到 NavDemoFragmentA 为例子演示应该如何使用`safeargs`进行安全的值传递,
      
      首先将为`lib_navigation_demo_nav` C 到 A 的配置代码修改为
      
      ```xml
      <?xml version="1.0" encoding="utf-8"?>
      <navigation xmlns:android="http://schemas.android.com/apk/res/android"
          xmlns:app="http://schemas.android.com/apk/res-auto"
          xmlns:tools="http://schemas.android.com/tools"
          android:id="@+id/lib_navigation_demo_nav"
          app:startDestination="@id/fragmentA"
          tools:ignore="UnusedNavigation">
      
         <--!-->......</--!-->
      
           <--!-->......</--!-->
      
          <fragment
              android:id="@+id/fragmentC"
              android:name="com.killeTom.navigation.fragment.NavDemoFragmentC"
              android:label="fragmentC"
              tools:layout="@layout/nav_demo_fragment_c">
      
              <action
                  android:id="@+id/action_fragmentC_to_fragmentA"
                  app:destination="@id/fragmentA"
                  app:exitAnim="@android:anim/slide_out_right" />
      
              <argument android:name="message"
                  android:defaultValue=""
                  app:argType="string"/>
      
              <argument android:name="result"
                  app:argType="boolean"
                  android:defaultValue="false"/>
      
      
          </fragment>
      </navigation>
      ```
      
      然后查看下是否能够动态生成相应的Args代码，譬如这里示例代码对应为`NavDemoFragmentCArgs`文件。注意如果没有及时动态生成，重新 build 构建当前模块尝试生成即可。
      
      ```kotlin
      data class NavDemoFragmentCArgs(
        val message: String = "",
        val result: Boolean = false
      ) : NavArgs {
        fun toBundle(): Bundle {
          val result = Bundle()
          result.putString("message", this.message)
          result.putBoolean("result", this.result)
          return result
        }
      
        companion object {
          @JvmStatic
          fun fromBundle(bundle: Bundle): NavDemoFragmentCArgs {
            bundle.setClassLoader(NavDemoFragmentCArgs::class.java.classLoader)
            val __message : String?
            if (bundle.containsKey("message")) {
              __message = bundle.getString("message")
              if (__message == null) {
                throw IllegalArgumentException("Argument \"message\" is marked as non-null but was passed a null value.")
              }
            } else {
              __message = ""
            }
            val __result : Boolean
            if (bundle.containsKey("result")) {
              __result = bundle.getBoolean("result")
            } else {
              __result = false
            }
            return NavDemoFragmentCArgs(__message, __result)
          }
        }
      }
      ```
      
       当对应的文件生成后，我们可以这样使用达到一个值类型安全传递的效果：
      
      ```kotlin
      class NavDemoFragmentC :Fragment() {
      
          override fun onCreateView(
              inflater: LayoutInflater,
              container: ViewGroup?,
              savedInstanceState: Bundle?
          ): View? {
              //省略部分不相干代码
      
              //构建budle
                  val bundle = bundleOf(
                      "message" to "ok",
                      "result" to true
                  )
      		//利用bundle 构建出对应的 Args
                  val args = NavDemoFragmentCArgs.fromBundle(bundle)
              //利用args 获取结果bundle 并作为值传递过去
              	NavHostFragment.findNavController(this)
              			.navigate(R.id.action_fragmentC_to_fragmentA,args.toBundle())
              
              //省略部分不相干代码
          }
          
      }
      ```
      
      

5. 常见的一些错误信息

   针对一些场景操作导致的常见错误，以下将会进行一些讲解分析。
   
   

## Navigation 原理解析



## 总结


```

```