(ns umlautify.tags
  (:require [umlautify.tags :refer [with-tags]])
  )

(defmacro exploratory [any] (with-tags any "exploratory"))
(defmacro green "should make the code green" [any]
  any
  )
(defmacro yellow "should make the code yellow" [any]
  any)

(yellow "butterfly")
(yellow "somethingelse")
()
;; POTATO
;; potato



tomato
(yellow "|asdfa")


(yellow "sadfas")
