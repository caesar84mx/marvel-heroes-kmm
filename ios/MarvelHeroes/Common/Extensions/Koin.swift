//
//  Koin.swift
//  SportCast
//
//  Created by Maxim Dymnov on 10/3/20.
//

import shared

private var _koin: Koin_coreKoin? = nil
var koin: Koin_coreKoin {
    _koin!
}

func launchKoin() {
    _koin = DiKt.doInitKoin(appModules: []).koin
}
