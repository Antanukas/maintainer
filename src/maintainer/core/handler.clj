(ns maintainer.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as middleware]
            [maintainer.core.achievements-controller :as achievements-ctrl]))

(defroutes site-routes
  (GET "/" [] "Hello World")
  (route/resources "/")
  (route/not-found "Not Found"))

(defroutes api-routes
  (GET "/rest/teams" [] achievements-ctrl/get-teams)
  (GET "/rest/teams/:team/achievements" [team] (achievements-ctrl/get-achievements team))
  (GET "/rest/teams/:team/achievements/buildBreaker" [team] (achievements-ctrl/get-build-breaker-top team)))

(def site (wrap-defaults site-routes site-defaults))
(def rest-api
  (-> (handler/api api-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))

(def app (routes rest-api site))
