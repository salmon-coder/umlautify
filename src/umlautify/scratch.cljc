(ns umlautify.scratch)

(defmacro defc [name value]
  (if (instance? clojure.lang.Symbol name)
    nil
    (throw (IllegalArgumentException. "First argument to defc must be a symbol")))
   (list 'def (with-meta name {}) value))

; given a function name, its args and body, create 2 versions:
; ie (double-it foo [] ) should create 2 functions: foo and foo*
(defmacro double-it                
  [fname args & body]         
  `(do (defn ~fname ~args ~@body)
       (defn ~(symbol (str fname "*")) ~args ~@body)))
(double-it afunc [str] (println str))

(defmacro defi
  [fname val]
  `(do
 (defn ~fname ~[]  ~val)
     (def ~fname ~val)
    ))

(defi y 4)
y
(y)
t
(t)
(defc x 2)







