//
//  HeroesItemUIElement.swift
//  MarvelHeroes
//
//  Created by Maxim Dymnov on 11/30/20.
//

import Foundation
import shared

struct HeroesItemUIElement: Identifiable {
    let id: UUID = UUID()
    let name: String
    let team: String
    let publisher: String
    let avatar: String
}

extension Hero {
    func toUiModel() -> HeroesItemUIElement {
        HeroesItemUIElement(
            name: name,
            team: team,
            publisher: publisher,
            avatar: avatar
        )
    }
}
