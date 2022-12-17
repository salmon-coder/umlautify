(ns umlautify.views
  (:require
   [re-frame.core :as re-frame :refer [subscribe dispatch reg-event-db ]]
   [umlautify.subs :as subs]
   [umlautify.macros :as macros
    :refer [some-macro deftag]
    ]
   [umlautify.events :as events]
   [umlautify.tags :as tags :refer
    [exploratory]
    ]
   [umlautify.box :refer [main-view]]
   ))



(defn loading-view []
  [:div
   (tags/did-load)
   ]
  )

(defn starter-banner []
  (let [name (re-frame/subscribe [::subs/name])]
                           [:div
                            [:h1
                             "Hello from make a change here " @name]
                            ]))


(exploratory
 (defn text-input
   [value callback-fn]
   [:input
    {:type "text"
     :value value
     :on-change #(callback-fn (-> % .-target .-value))
     }
    ]
   ))
(exploratory
 (reg-event-db
  :beacon
  (fn [db [_ new-]])
  )
 )
(exploratory
 (reg-event-db
  :set-flag
  (fn [db [_ new-value]]
    (assoc db :flag new-value)
    )
  )
 )

(defn simple-text-input
  [value callback-fn]
  [:input
   {:type "text"
    :value value
:on-change #(callback-fn (-> % .-target .-value))}
]) ;; callback with value

(defn customer-names
  []
  (let [customer @(subscribe [:customer])]
    (prn "customer like" customer)
    [:div "Name:"
     [simple-text-input
      (:first-name customer)
      #(dispatch [:change-customer 
                   {:first-name %}])]
     [simple-text-input
      (:last-name customer)
      #(dispatch [:change-customer
                 { :last-name %}])]]))

(defn umlautify-app []
  [:input ]
  )

(defn debugger []
  (dispatch [:beacon true])
 "this is the debugging panel:"
  )

(defn green-vew [])

(defn accepts-children [])

(defn main-panel "the entrypoint of the app"
  []
  [:div
   (loading-view)
   (debugger)
   (starter-banner)
   (starter-banner)
   (main-view)
   (customer-names)
   (umlautify-app)
   (green-view)
   ])
