(ns maintainer.core.gitlab-client
  (:require [clj-http.client :as client]
            [clojure.core.memoize :as memo]
            [maintainer.config.config :refer [config]]))

(defn- get-gitlab [path]
  (:body
   (client/get (str (:gitlab-root config) path)
               { :query-params {"private_token" (:private-token config) }
                 :as :json })))

(defn- get-all-projects []
  (println "Test get all projects")
  (get-gitlab "projects"))

(def get-all-projects-cachable
  (memo/ttl get-all-projects :ttl/threshold (:cache-threshold config)))

(defn get-project-by-name [project-name]
  (let [projects (get-all-projects-cachable)]
    (first (filter #(= (:name %1) project-name) projects))))

(defn get-contributors [project]
  (let [project-id (:id project)]
    (get-gitlab (str "projects/" project-id "/repository/contributors"))))
