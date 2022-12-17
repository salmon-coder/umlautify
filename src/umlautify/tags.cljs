(ns umlautify.tags
  (:require-macros [umlautify.tags])
  (:require [clojure.set :as set]
            [clojure.test :as test :refer [deftest is testing]]
            ))


(defn test-success-message []
  (dotimes [n 20](prn "########### ALL TEST SUCCESS ##########" n)))
  

(defn do-nothing [] nil)

(def ^:const supported-tags
  #{"exploratory" "view" "core" "scratch" nil 1 2 3})


(defn get-tags [form] (or (:tags (meta form)) #{}))
(defn set-tags [form tags] (with-meta form {:tags tags}))
(defn is-tag? [tag] (contains? supported-tags tag))
(defn all-tags? [maybe-coll]
  (cond
    (not (coll? maybe-coll)) (is-tag? maybe-coll)
    :else (every? all-tags? maybe-coll)))

(defn set-join [s1 s2]
  (set/union (into #{} s1) (into #{} s2)))




(defn with-tags
  ([form tags]
   (assert (all-tags? tags) "check input, maybe not all tags valid")
   (let [joined-tags (set-join (get-tags form) tags)](set-tags form joined-tags)))
  ([form tag1 & tags]
   (assert (is-tag? tag1) "check input")
   (assert (all-tags? tags) "check tags")
   (let [marginal-tags (set-join #{tag1} tags)
         joined-tags (set-join (get-tags form) marginal-tags)]
     (set-tags form joined-tags))))


(deftest add-tags-to-var
  "add some tags to the var"
  (let [x {}
        y (with-tags {} #{1 2 3})
        z (with-tags {} 1 2 3)
        x-tags (get-tags x)
        y-tags (get-tags y)
        z-tags (get-tags z) ]

    (is (= #{} x-tags))
    (is (= {} x))
    (is (= {} y))
    (is (= {} z))
    (is (= #{2 1 3} y-tags))
    (is (= #{3 1 2} z-tags))))


(add-tags-to-var)


(defonce defined-tags (atom #{}))
(defmacro deftag
  "allows users to define a tag which is a way of
  annotating something that is a software unit"
  [name {:keys [color priority] :as opts}]
  (defmacro name [] (comment []))
  )




;; (defmacro exploratory [] nil)
(defn exploratory [any] any)



(defn did-load [] "tags-did-load")
