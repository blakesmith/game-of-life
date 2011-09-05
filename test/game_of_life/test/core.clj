(ns game-of-life.test.core
  (:use [game-of-life.core])
  (:use [clojure.test]))

(deftest querying-cell-at-x-y
  (let [world [[{:alive false :x 0 :y 0}
                {:alive true :x 1 :y 0}
                {:alive false :x 2 :y 0}]]]
    (is (= {:alive true :x 1 :y 0} (cell-at [1 0] world)))))

(deftest not-alive-if-fewer-than-two-neighbors
  (let [world [[{:alive false :x 0 :y 0}
                {:alive true :x 1 :y 0}
                {:alive true :x 2 :y 0}]]]
    (is (= false (alive-next? (cell-at [1 0] world) world)))))

(deftest alive-if-two-neighbors-alive
  (let [world [[{:alive true :x 0 :y 0}
                {:alive true :x 1 :y 0}
                {:alive true :x 2 :y 0}]]]
    (is (= true (alive-next? (cell-at [1 0] world) world)))))

(deftest alive-if-three-neighbors-alive
  (let [world [[{:alive true :x 0 :y 0}
                {:alive true :x 1 :y 0}
                {:alive true :x 2 :y 0}]
               [{:alive false :x 0 :y 1}
                {:alive true :x 1 :y 1}
                {:alive false :x 2 :y 1}]
               [{:alive false :x 0 :y 2}
                {:alive false :x 1 :y 2}
                {:alive false :x 2 :y 2}]]]
    (is (= true (alive-next? (cell-at [1 1] world) world)))))

(deftest dead-if-more-than-three-neighbors-alive
  (let [world [[{:alive true :x 0 :y 0}
                {:alive true :x 1 :y 0}
                {:alive true :x 2 :y 0}]
               [{:alive true :x 0 :y 1}
                {:alive true :x 1 :y 1}
                {:alive false :x 2 :y 1}]
               [{:alive false :x 0 :y 2}
                {:alive false :x 1 :y 2}
                {:alive false :x 2 :y 2}]]]
    (is (= false (alive-next? (cell-at [1 1] world) world)))))

(deftest dead-doesnt-come-back-to-life-if-more-than-two-neighbors
  (let [world [[{:alive true :x 0 :y 0}
                {:alive true :x 1 :y 0}
                {:alive false :x 2 :y 0}]
               [{:alive false :x 0 :y 1}
                {:alive false :x 1 :y 1}
                {:alive false :x 2 :y 1}]
               [{:alive false :x 0 :y 2}
                {:alive false :x 1 :y 2}
                {:alive false :x 2 :y 2}]]]
    (is (= false (alive-next? (cell-at [1 1] world) world)))))

(deftest blinker-test
  (let [world [[{:alive false :x 0 :y 0}
                {:alive false :x 1 :y 0}
                {:alive false :x 2 :y 0}]
               [{:alive true :x 0 :y 1}
                {:alive true :x 1 :y 1}
                {:alive true :x 2 :y 1}]
               [{:alive false :x 0 :y 2}
                {:alive false :x 1 :y 2}
                {:alive false :x 2 :y 2}]]]

    (is (= [[{:alive false :x 0 :y 0}
             {:alive true :x 1 :y 0}
             {:alive false :x 2 :y 0}]
            [{:alive false :x 0 :y 1}
             {:alive true :x 1 :y 1}
             {:alive false :x 2 :y 1}]
            [{:alive false :x 0 :y 2}
             {:alive true :x 1 :y 2}
             {:alive false :x 2 :y 2}]]
         (next-generation world)))))

(deftest handles-asymmetric-world
  (let [world [[{:alive true :x 0 :y 0}
                {:alive true :x 1 :y 0}
                {:alive true :x 2 :y 0}]]]
    (is (= [[{:alive false :x 0 :y 0} {:alive true :x 1 :y 0} {:alive false :x 2 :y 0}]]
         (next-generation world)))))

