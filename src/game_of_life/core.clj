(ns game-of-life.core)

(def neighbor-cords
  [[-1 -1]
   [0 -1]
   [1 -1]
   [-1 0]
   [1 0]
   [-1 1]
   [0 1]
   [1 1]])

(defn cell-at [loc world]
  (let [[x y] loc
        alive (get (get world y) x)]
    {:alive alive :x x :y y}))

(defn find-neighbors [cell world]
  (let [x (cell :x) y (cell :y)]
    (map
      (fn [cord]
        (cell-at [(+ x (first cord)) (+ y (second cord))]
                 world))
      neighbor-cords)))

(defn alive-next? [cell world]
  (let [total-neighbors
    (count
      (filter #(= true (% :alive)) (find-neighbors cell world)))]
    (or
      (and (not (cell :alive)) (= total-neighbors 3))
      (and (cell :alive) (or (= total-neighbors 2) (= total-neighbors 3))))))
