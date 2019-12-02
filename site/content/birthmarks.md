---
title: "Birthmarks"
date: 2018-06-13
draft: false
weight: 100
---

## Preliminaries

### Definition of Birthmarks

Let $p$, and $q$ be a given programs, and $\mathcal{B}_f(p)$ be set of characteristics extracted from $p$ by a given method $f$.
If the conditions below are met, $\mathcal{B}_f(p)$ is said to be a birthmark of $p$.

* Condition 1: $\mathcal{B}_f(p)$ is obtained from only program $p$.
* Condition 2: If $q$ is a copy of $p$, then $\mathcal{B}_f(p) = \mathcal{B}_f(q)$.

Condition 1 indicates that a birthmark is an information necessary for running $p$ and is not additional information.
In other words, birthmark does not require additional information in the method of a software watermark.
Condition 2 indicates that same birthmark can be obtained from a copied program.
If birthmarks $\mathcal{B}_f(p)$ and $\mathcal{B}_f(q)$ are different, this means that $q$ is not a copy of $p$.

Two properties known as resilience and credibility should also ideally be satisfied.

* Resilience
    * For a $p'$ obtained by an arbitrary equivalent transformation of $p$, $\mathcal{B}_f(p) = \mathcal{B}_f(p')$ is satisfied.
* Credibility
    * When programs $p$ and $q$ that develop independently, $\mathcal{B}_f(p)\neq \mathcal{B}_f(q)$ is satisfied.

Resilience property indicates a resistance of birthmark to various types of attacks.
Credibility property indicates that programs created completely independently can be differentiated even if their specifications are the same.
Because birthmarks that completely satisfy both these properties are difficult to create, in practice, birthmark strength must be set appropriately at the discretion of the user.


### Types of Birthmarks



### Similarities





