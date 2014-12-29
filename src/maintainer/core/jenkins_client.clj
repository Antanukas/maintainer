(ns maintainer.core.jenkins-client
  (:require [clj-http.client :as client]
            [maintainer.config.config :refer [config]]))

(defn get-jenkins [path]
  (:body (client/get (str (:jenkins-root config) path)
                     {:as :json})))

(defn get-view-builds [view-name]
  (get-jenkins (str "view/" view-name "/api/json?pretty=true&tree=jobs[name,builds[result,number,culprits[fullName]]]")))
