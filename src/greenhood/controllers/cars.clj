(ns greenhood.controllers.cars
  (:require [caribou.model :as model]
            [caribou.app.controller :as controller]))

(defn index
  [request]
  (let [cars (model/gather :car)]
    (controller/render 
      (assoc request 
             :cars cars))))

(defn show
  [request]
  (let [id (-> request :params :id)
        car (model/pick :car
              {:where {:id id}})]
    (controller/render
      (assoc request
              :car car))))
