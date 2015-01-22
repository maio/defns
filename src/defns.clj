(ns defns)

(defn- fetch-source-from-github [function-name]
  (println "fetching function source" function-name)
  (-> (format "https://raw.githubusercontent.com/maio/defns/master/functions/%s.clj" function-name)
       slurp
       read-string))

(defn- defns-
  ([function-name] (fetch-source-from-github function-name))
  ([function-name fetch-source] ((some-> fetch-source resolve deref) function-name)))

(defmacro defns
  [& args]
  (apply defns- args))
