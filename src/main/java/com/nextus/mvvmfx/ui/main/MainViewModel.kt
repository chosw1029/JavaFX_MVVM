package com.nextus.mvvmfx.ui.main

import com.nextus.mvvmfx.scope.FxScope
import com.nextus.mvvmfx.scope.ScreenScope
import com.nextus.mvvmfx.ui.base.BaseViewModel
import de.saxsys.mvvmfx.ScopeProvider

@ScopeProvider(scopes = [FxScope::class, ScreenScope::class])
class MainViewModel : BaseViewModel() {

    override fun initialize() {

    }

}