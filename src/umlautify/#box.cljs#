(ns umlautify.box
  (:require [re-frame.core :as re-frame])
  )


(defn base-box
  ([props children]
   [:div props children])
  ([children] (base-box {} children))
  ([] (base-box {} nil))
  ([props child1 & rest]
   [:div props child1 rest]
   )
  )


(defn border [{:keys [color width] :as props} children]
  [:div {:style {:color "red"}} children])

(defn box
  "extends re-frame div to use metadata. in this case,
  metadata is data that is relevant to the developer,
  but not relevant to the user."
  [this]
  [:div this])
(defn hello [] [:div "hello"])
(defn red-hello [] [:div {:style {:color "red"}} "hello"])

(defn my-box [] (base-box nil nil))

(defn main-view "entrypoint" []
  [:div
   (red-hello)
   (base-box
    (base-box (hello) (hello) (hello) (hello) )
    (base-box (hello) {:style {:color "red"}}))]
  [:div {:style {:color "red"}} (hello) (hello) (hello) {:style {:color "red"}}]
  )
