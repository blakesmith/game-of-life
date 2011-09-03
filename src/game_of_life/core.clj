(ns game-of-life.core)

(defn cell-at [loc world]
  (let [[x y] loc
        alive (nth (nth world y) x)]
    {:alive alive :x x :y y}))

(defn alive-next? [cell world]
  false)
