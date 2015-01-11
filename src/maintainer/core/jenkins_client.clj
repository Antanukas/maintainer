(ns maintainer.core.jenkins-client
  (:require [clj-http.client :as client]
            [maintainer.config.config :refer [config]]))

(defn- get-jenkins [path]
  (let [url (str (:jenkins-root config) path)]
      (println "GET Jenkins full URL: " url)
      (:body (client/get url
                         {:as :json}))))

(defn get-view-builds [view-name]
  (get-jenkins (str "view/" view-name "/api/json?pretty=true&tree=jobs[name,builds[result,number,culprits[fullName]]]")))
