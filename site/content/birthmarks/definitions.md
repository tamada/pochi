---
title: "Definitions（諸定義）"
date: 2017-08-29T13:00:00+09:00
draft: false
slug: definitions
categories: [ "Birthmarks", "Document" ]
---

# Definitions

## Definition of Birthmarks

Let $p$, and $q$ be a given programs, and $f(p)$ be set of characteristics extracted from $p$ by a given method $f$.
If the conditions below are met, $\mathcal{B} = f(p)$ is said to be a birthmark of $p$.

* Condition 1: $f(p)$ is obtained from only program $p$.
* Condition 2: If $q$ is a copy of $p$, then $\mathcal{B}(p) = \mathcal{B}(q)$.

Condition 1 indicates that a birthmark is an information necessary for running $p$ and is not additional information.
In other words, birthmark does not require additional information in the method of a software watermark.
Condition 2 indicates that same birthmark can be obtained from a copied program.
If birthmarks $\mathcal{B}(p)$ and $\mathcal{B}(q)$ are different, this means that $q$ is not a copy of $p$.

Two properties known as resilience and credibility should also ideally be satisfied.

* Resilience
    * For a $p'$ obtained by an arbitrary equivalent transformation of $p$, $\mathcal{B}(p) = \mathcal{B}(p')$ is satisfied.
* Credibility
    * When programs $p$ and $q$ that develop independently, $\mathcal{B}(p)\neq \mathcal{B}(q)$ is satisfied.

Resilience property indicates a resistance of birthmark to various types of attacks.
Credibility property indicates that programs created completely independently can be differentiated even if their specifications are the same.
Because birthmarks that completely satisfy both these properties are difficult to create, in practice, birthmark strength must be set appropriately at the discretion of the user.

## Types of Birthmarks



## Similarities




