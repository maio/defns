(ns defns-test
  (:require [clojure.test :refer :all])
  (:use defns))

(defn fetch-local-source [function-name]
  (read-string
   (slurp (clojure.java.io/file (format "./functions/%s.clj" function-name)))))

(defns deaccent fetch-local-source)

(deftest deaccent-function
  (are [input output] (= (deaccent input) output)
       ""
       ""

       "any string without accents"
       "any string without accents"

       "Příliš žluťoučký kůň úpěl ďábelské kódy."
       "Prilis zlutoucky kun upel dabelske kody."))
