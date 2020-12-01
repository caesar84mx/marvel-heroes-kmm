//
//  TaskResult.swift
//  OttoCast
//
//  Created by Maxim Dymnov on 10/27/20.
//

enum TaskStatus<T: Any> {
    case success(data: T)
    case error(errorMessage: String)
    case loading
}
