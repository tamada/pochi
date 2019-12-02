---
title: "Birthmarks"
date: 2018-06-13
draft: false
weight: 100
---

## Definition of Birthmarks

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

## Types of Birthmarks

By following the definition above, many researchers proposed the different types of birthmarks.
Differences in birthmark types are caused by focusing on different parts of programs.
For example, control flows, opcode sequences, data flows, behaviors, and structures of programs are the general information for the proposed birthmarks.

Structures of birthmarks from extracted information also make different types of birthmarks. 
For example, different types of birthmarks are proposed from opcode sequences by unmodified sequence, frequencies, and k-gram based sequences.
We categorized the structures of birthmarks into sequences, set (unordered set), vectors, and graphs.


## Similarities

Each birthmark has own similarity calculation algorithm.
In other words, to compare $\mathcal{B}_f(p)$ and $\mathcal{B}_f(q)$ extracted from $f$ should use a certain method $\mathrm{sim}(\mathcal{B}_f(p), \mathcal{B}_f(q))$.
Besides, the range of $\mathrm{sim}(\mathcal{B}_f(p), \mathcal{B}_f(q))$ is $[0, 1]$,
Besides, the range of $\mathrm{sim}(\mathcal{B}_f(p), \mathcal{B}_f(q))$ is $[0, 1]$.
$0$ means two programs $p$ and $q$ are completely different, and $1$ means either is strongly suspects of copy from other.

Then, we introduce threshold $\varepsilon$ to show the suspecting rate.
We categorize resultant similarities into three groups below by $\varepsilon$.

\begin{eqnarray}\mathrm{sim}(\mathcal{B}_f(p), \mathcal{B}_f(q)) = \begin{cases}\geq \varepsilon      & \textit{copy relation}\\\\ \leq 1 - \varepsilon  & \textit{no copy relation}\\\\ \textit{otherwise}    & \textit{inconclusive}\end{cases}\end{eqnarray}

Note that, the typical value of $\varepsilon$ is 0.75.

## Theft detection process by birthmarks

The goal of software birthmarks is detecting the suspects of copies, not prove the theft.
Therefore, the birthmark methods require to examine a huge amount of programs and to detect the suspects.
After detecting the suspects by the birthmark methods, we must inspect the details of pairs to prove the theft.
Because the birthmark methods bury no information.

{{< mermaid >}}
graph LR
    id1(Collection);
    id2(Extraction);
    id3(Comparison);
    id4(Examination);
    id5(Post-process);
    subgraph "Birthmark System"
        id1 --> id2;
        id2 --> id3;
        id3 --> id4;
    end
    id4 --> id5;
    click id1 openDetails "collection"
    click id2 openDetails "extraction"
    click id3 openDetails "comparison"
    click id4 openDetails "examination"
    click id5 openDetails "postprocess"
{{</ mermaid >}}

