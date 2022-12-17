(ns umlautify.macros)
(defonce tags (atom {}))
(defmacro some-macro [] nil)
(defmacro deftag [name opts]
 '(defmacro (keyword (str name)))
  (assert (not (contains? (deref tags) (str name)))
          "looks like that name may be already taken" )
  nil
  )
(defmacro exploratory [form] form)
