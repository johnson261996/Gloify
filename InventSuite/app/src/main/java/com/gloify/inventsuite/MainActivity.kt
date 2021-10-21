package com.gloify.inventsuite



import android.content.ClipData.Item
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.adriankuta.expandable_recyclerview.expandableTree
import com.gloify.inventsuite.Fragments.*
import com.gloify.inventsuite.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView




class MainActivity : AppCompatActivity(){

    //lateinit var mdrawer:DrawerLayout
    //private lateinit var navView: NavigationView
    lateinit var binding: ActivityMainBinding
    private var drawerToggle: ActionBarDrawerToggle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val window: Window = window

         // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

         // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))

        binding.drawerLayout.findViewById<DrawerLayout>(R.id.drawerLayout)
        drawerToggle = setupDrawerToggle()
        setupDrawerContent(binding.navigationView)

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle!!.setDrawerIndicatorEnabled(true)
        drawerToggle!!.syncState()

        // Tie DrawerLayout events to the ActionBarToggle
        binding.drawerLayout.addDrawerListener(drawerToggle!!)

        val tree = expandableTree("World") {
            child(resources.getString(R.string.Dashboard)) {
                 // child(getResources().getString(R.string.))
            }

            child(resources.getString(R.string.Product)) {
                child(resources.getString(R.string.add_products)) {
                    child(resources.getString(R.string.view_products))
                }
                child("Germany")
            }
            child("Asia") {
                child("China")
            }
        }

        with(binding) {
            val adapter = ExpandableListAdapter()
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            recyclerView.adapter = adapter

            adapter.setTree(tree)
        }
    }







    private fun setupDrawerToggle(): ActionBarDrawerToggle? {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.

        return ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
       navigationView.setNavigationItemSelectedListener { item ->
           selectDrawerItem(item)
           true
       }
    }

    private fun selectDrawerItem(item: MenuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked

        // Create a new fragment and specify the fragment to show based on nav item clicked
        var fragment: Fragment? = null
        val fragmentClass: Class<*>
        fragmentClass = when (item.itemId) {
            R.id.dashboard -> DashboardFragment::class.java
            R.id.product -> ProductFragment::class.java
            R.id.coupon -> CouponsFragment::class.java
            R.id.customer -> CustomersFragment::class.java
            R.id.Employees -> EmployeesFragment::class.java
            R.id.Vendors -> VendorsFragment::class.java
            R.id.store -> StoreFragment::class.java
            R.id.warehouse -> WarehousesFragment::class.java
            R.id.salesorder -> SalesOrderFragment::class.java
            R.id.addnewstore -> AddNewStoreFragment::class.java
            else -> DashboardFragment::class.java
        }

        try {
            fragment = fragmentClass.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Insert the fragment by replacing any existing fragment

        // Insert the fragment by replacing any existing fragment
        val fragmentManager: FragmentManager = supportFragmentManager
        if (fragment != null) {
            fragmentManager.beginTransaction().replace(R.id.hostFragment, fragment).commit()
        }

        // Highlight the selected item has been done by NavigationView

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true)
        // Set action bar title
        // Set action bar title
        //setTitle(item.getTitle())
        // Close the navigation drawer

        binding.drawerLayout.closeDrawers()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->
                binding.drawerLayout.openDrawer(GravityCompat.START)
        }

    return true
    }
}

