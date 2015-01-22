;; http://www.mnot.net/cache_docs/
(defn ring-wrap-cache-forever
  "Wrap an app such that all request will be cached forever. This is
  useful for immutable content.

  Any request which contains If-Modified-Since or If-None-Match
  headers will get HTTP 304 Not-Modified response.

  All responses will get Cache-Control max-age set to 3167356 seconds
  so that browser will keep the cached file around for some time."
  [handler]
  (fn [{headers :headers :as req}]
    (if (or (headers "if-modified-since")
            (headers "if-none-match"))
      (ring.util.response/status {} 304)
      (when-let [res (handler req)]
        (ring.util.response/header res "Cache-Control" "max-age=31556926, public")))))
