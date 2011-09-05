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

(deftest alive-if-three-neighbors-alive
  (let [world [[true true true]
               [false true false]
               [false false false]]]
    (is (= true (alive-next? (cell-at [1 1] world) world)))))

(deftest dead-if-more-than-three-neighbors-alive
  (let [world [[true true true]
               [true true false]
               [false false false]]]
    (is (= false (alive-next? (cell-at [1 1] world) world)))))

(deftest dead-doesnt-come-back-to-life-if-more-than-two-neighbors
  (let [world [[true true false]
               [false false false]
               [false false false]]]
    (is (= false (alive-next? (cell-at [1 1] world) world)))))

(deftest blinker-test
  (let [world [[false false false]
               [true true true]
               [false false false]]]
    (is (= [[false true false]
            [false true false]
            [false true false]]
          (next-generation world)))))

