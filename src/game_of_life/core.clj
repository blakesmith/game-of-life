(ns game-of-life.core)

(def neighbor-cords
  [[-1 0]
   [1 0]])

(defn cell-at [loc world]
  (let [[x y] loc
        alive (nth (nth world y) x)]
    {:alive alive :x x :y y}))

(defn find-neighbors [cell world]
  (let [x (cell :x) y (cell :y)]
    (map
      (fn [cord]
        (cell-at [(+ x (first cord)) (+ y (second cord))]
                 world))
      neighbor-cords)))

(defn alive-next? [cell world]
  (<= 2
    (count
      (filter #(= true (% :alive)) (find-neighbors cell world)))))
