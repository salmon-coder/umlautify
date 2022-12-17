(ns umlautify.events
  (:require
   [re-frame.core :as re-frame :refer [reg-event-db]]
   [umlautify.db :as db]
   ))

(def seed-data "seeeeed"
  {:customer {:first-name "Joe" :last-name "Schmoe"}}
  )

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   (merge db/default-db seed-data)))


(reg-event-db
 :change-customer
 (fn [db [_ {:keys [first-name last-name] :as customer}]]
   (let [customer-old (:customer db)
         first-name-old (:first-name customer-old)
         last-name-old (:last-name customer-old)
         first-name-new (or first-name first-name-old)
         last-name-new (or last-name last-name-old)]
     (assoc db :customer {:first-name first-name-new
                          :last-name last-name-new}))))


