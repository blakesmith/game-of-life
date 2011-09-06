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

(defn create-cell [alive x y]
  {:alive alive :x x :y y})

(defn cell-at [loc world]
  (let [[x y] loc]
    (get (get world y) x)))

(defn find-neighbors [cell world]
  (let [x (cell :x) y (cell :y)]
    (remove nil? (map
      (fn [cord]
        (cell-at [(+ x (first cord)) (+ y (second cord))]
                 world))
      neighbor-cords))))

(defn alive-next? [cell world]
  (let [total-neighbors
          (count
          (filter #(= true (% :alive)) (find-neighbors cell world)))]
    (or
      (and (not (cell :alive)) (= total-neighbors 3))
      (and (cell :alive) (or (= total-neighbors 2) (= total-neighbors 3))))))

(defn apply-to-world [f world]
  (let [rows (count world)
        cols (count (first world))]
    (vec (map
      (fn [row y]
        (vec (map
          (fn [_ x]
            (f x y))
          row (range cols))))
      world (range rows)))))

(defn next-generation [world]
  (apply-to-world
    (fn [x y]
      (create-cell (alive-next? (cell-at [x y] world) world) x y))
    world))

(defn new-world [width height]
  (let [world (map (fn [_] (range width)) (range height))]
    (apply-to-world
      (fn [x y]
        (create-cell false x y))
      world)))

