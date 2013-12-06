(defproject greenhood "0.13.0"
  :description "The page routing ring handler for caribou"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [http-kit "2.1.12"]
                 [org.immutant/immutant "1.0.2"]
                 [caribou/caribou-admin "0.13.0"]
                 [caribou/caribou-api "0.13.0"]
                 [org.clojure/tools.nrepl "0.2.3"]]
  :plugins [[lein-ring "0.8.6"]
            [caribou/lein-caribou "2.13.0"]
            [lein-cljsbuild "0.3.3"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n" "-Xmx2g" "-XX:MaxPermSize=128m"]
  :source-paths ["src"]
  :resource-paths ["resources/"]
  :min-lein-version "2.0.0"
  :migration-namespace greenhood.migrations
  :main greenhood.core
  :ring {:handler greenhood.core/handler
         :init greenhood.core/init
         :port 33333
         :auto-reload? false
         :servlet-name "greenhood-frontend"}
  :immutant {:context-path "/"
             :init greenhood.immutant/init}
  :cljsbuild {:repl-listen-port 44994
              :builds
              [{:source-paths ["resources/cljs"]
                :compiler {:output-to "resources/public/js/app/skel.js"
                           :optimizations :whitespace
                           :pretty-print true}}]})
