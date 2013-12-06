(ns greenhood.boot
  (:require [caribou.core :as caribou]
            [caribou.config :as config]
            [caribou.app.config :as app-config]))

(def local-config
  {:app {:use-database true
         :public-dir "public"
         :default-locale "global"
         :localize-routes ""}
   :assets {:dir "app/"
            :prefix nil
            :root ""}
   :aws {:bucket nil
         :credentials nil}
   :cljs {:root "resources/cljs"
          :reload false
          :options {:output-to "resources/public/js/app/greenhood.js"
                    :output-dir "resources/public/js/app/out"
                    :pretty-print true}
          :brepl {:listen false
                  :port 44994
                  :path "repl"}}
   :controller {:namespace "greenhood.controllers"
                :reload true}
   :database {:classname    "org.h2.Driver"
              :subprotocol  "h2"
              :host         "localhost"
              :protocol     "file"
              :path         "/tmp/"
              :database     "taiga_development"
              :user         "h2"
              :password     ""}
   :error {:show-stacktrace false
           :catch-exceptions true}
   :field {:namespace "greenhood.fields"
           :slug-transform [[#"['\"]+" ""]
                            [#"[_ \\/?%:#^\[\]<>@!|$&*+;,.()]+" "-"]
                            [#"^-+|-+$" ""]]}
   :hooks {:namespace "greenhood.hooks"}
   :index {:path "caribou-index"
           :default-limit 1000}
   :logging {:loggers [{:type :stdout :level :debug}]}
   :nrepl {:port nil}
   :query {:enable-query-cache  false
           :query-defaults {}}
   :template {:cache-strategy :never}})

(defn boot
  []
  (let [default (app-config/default-config)
        local (config/merge-config default local-config)
        config (config/config-from-environment local)]
    (caribou/init config)))
