(ns game-of-life.test.core
  (:use [game-of-life.core])
  (:use [clojure.test]))

(deftest querying-cell-at-x-y
  (let [world [[false true false]]]
    (is (= {:alive true :x 1 :y 0} (cell-at [1 0] world)))))

(deftest not-alive-if-fewer-than-two-neighbors
  (let [world [[false true true]]]
    (is (= false (alive-next? (cell-at [1 0] world) world)))))

(deftest alive-if-two-neighbors-alive
  (let [world [[true true true]]]
    (is (= true (alive-next? (cell-at [1 0] world) world)))))
