//
//  HomeScreenView.swift
//  MarvelHeroes
//
//  Created by Maxim Dymnov on 11/30/20.
//

import SwiftUI
import Combine
import shared

struct HomeScreenView: View {
    @ObservedObject private var interactor: HomeInteractor = HomeInteractor()
    
    let title = StringResources()
        .getStringFor(
            id: StringResources
                .KeysHome()
                .TITLE,
            language: .english
        )
    
    let emptyList = StringResources()
        .getStringFor(
            id: StringResources
                .KeysHome()
                .EMPTY_LIST_MESSAGE,
            language: .english
        )
    
    var body: some View {
        GeometryReader { geo in
            VStack {
                getView()
            }
            .onAppear { interactor.loadData() }
            .frame(width: geo.size.width, height: geo.size.height, alignment: .center)
        }
        .navigationBarTitle(Text(title))
    }
    
    @ViewBuilder private func getView() -> some View {
        switch interactor.requestStatus {
        case .success:
            if interactor.heroes.isEmpty {
                Text(emptyList)
            } else {
                List {
                    ForEach(interactor.heroes, id: \.id) { item in
                        HeroListItemView(hero: item)
                            .padding([.vertical], 2)
                    }
                }
            }
            
        case .error(errorMessage: let message):
            Text(message)
                .foregroundColor(.red)
        
        case .loading:
            Text("Loading...")
            
        case .none:
            EmptyView()
        }
    }
}



struct HeroListItemView: View {
    let hero: HeroesItemUIElement
    
    private let nameLabel = StringResources()
        .getStringFor(
            id: StringResources
                .KeysHome()
                .NAME_LABEL,
            language: .english
        )
    
    private let teamLabel = StringResources()
        .getStringFor(
            id: StringResources
                .KeysHome()
                .TEAM_LABEL,
            language: .english
        )
    
    private let publisherLabel = StringResources()
        .getStringFor(
            id: StringResources
                .KeysHome()
                .PUBLISHER_LABEL,
            language: .english
        )
    
    var body: some View {
        HStack {
            ImageView(
                withURL: hero.avatar,
                width: 80,
                height: 80
            )
            .padding(2)
            
            VStack(alignment: .leading, spacing: 5) {
                HStack {
                    Text(nameLabel)
                        .fontWeight(.bold)
                    VStack {
                        Text(hero.name)
                    }
                }
                
                HStack {
                    Text(teamLabel)
                        .fontWeight(.bold)
                    VStack {
                        Text(hero.team)
                    }
                }
                
                HStack {
                    Text(publisherLabel)
                        .fontWeight(.bold)
                    VStack {
                        Text(hero.publisher)
                    }
                }
            }
            .padding(2)
        }
    }
}

class HomeInteractor: ObservableObject {
    @Published var requestStatus: TaskStatus<Bool>? {
        willSet {
            objectWillChange.send()
        }
    }
    
    @Published var heroes: [HeroesItemUIElement] = [] {
        willSet {
            objectWillChange.send()
        }
    }
    
    private let viewModel: HomeViewModel = koin.get(objCClass: HomeViewModel.self, parameter: "HomeInteractor") as! HomeViewModel
    
    func loadData() {
        requestStatus = .loading
        viewModel.getHeroes { result in
            guard let errorMessage = result.errorMessage else {
                if let heroes = result.data as? [Hero] {
                    self.requestStatus = .success(data: true)
                    self.heroes = heroes.map { hero in hero.toUiModel() }
                }
                return
            }
            self.requestStatus = .error(errorMessage: errorMessage)
        }
    }
}

struct HomeScreenView_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreenView()
    }
}

struct HeroListItem_Previews: PreviewProvider {
    static var previews: some View {
        HeroListItemView(
            hero: HeroesItemUIElement(
                name: "Captain America",
                team: "Avengers",
                publisher: "Marvel Comics",
                avatar: "https://www.simplifiedcoding.net/demos/marvel/captainamerica.jpg"
            )
        )
    }
}
