(ns game-of-life.test.core
  (:use [game-of-life.core])
  (:use [clojure.test]))

(deftest querying-cell-at-x-y
  (let [world [[false true false]]]
    (is (= {:alive true :x 1 :y 0} (cell-at [1 0] world)))))

(deftest not-alive-if-fewer-than-two-neighbors
  (let [world [[false true false]]]
    (is (= false (alive-next? (cell-at [1 0] world) world)))))
