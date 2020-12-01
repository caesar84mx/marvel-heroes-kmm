//
//  ImageView.swift
//  MarvelHeroes
//
//  Created by Maxim Dymnov on 11/30/20.
//

import SwiftUI
import Combine

struct ImageView: View {
    @ObservedObject var imageLoader:ImageLoader
    @State var image:UIImage = UIImage()
    
    private let width: CGFloat
    private let height: CGFloat

    init(withURL url:String, width: CGFloat, height: CGFloat) {
        imageLoader = ImageLoader(urlString:url)
        self.width = width
        self.height = height
    }

    var body: some View {
            Image(uiImage: image)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width:width, height:height)
                .onReceive(imageLoader.didChange) { data in
                self.image = UIImage(data: data) ?? UIImage()
        }
    }
}

class ImageLoader: ObservableObject {
    var didChange = PassthroughSubject<Data, Never>()
    var data = Data() {
        didSet {
            didChange.send(data)
        }
    }

    init(urlString:String) {
        guard let url = URL(string: urlString) else { return }
        let task = URLSession.shared.dataTask(with: url) { data, response, error in
            guard let data = data else { return }
            DispatchQueue.main.async {
                self.data = data
            }
        }
        task.resume()
    }
}