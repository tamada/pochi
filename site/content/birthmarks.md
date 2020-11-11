---
title: ":books: Birthmarks"
date: 2020-10-08
draft: false
weight: 100
---

* [:green_book: Definition of Birthmarks](#definition-of-birthmarks)
* [:blue_book: Types of Birthmarks](#types-of-birthmarks)
* [:orange_book: Similarities](#similarities)
* [:closed_book: Theft Detection Process by Birthmarks](#theft-detection-process-by-birthmarks)

## :green_book: Definition of Birthmarks

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

{{< gototop >}}

## :blue_book: Types of Birthmarks

By following the definition above, many researchers proposed the different types of birthmarks.
Differences in birthmark types are caused by focusing on different parts of programs.
For example, control flows, opcode sequences, data flows, behaviors, and structures of programs are the general information for the proposed birthmarks.

Structures of birthmarks from extracted information also make different types of birthmarks. 
For example, different types of birthmarks are proposed from opcode sequences by unmodified sequence, frequencies, and k-gram based sequences.
We categorized the structures of birthmarks into sequences, set (unordered set), vectors, and graphs.


{{< gototop >}}

## :orange_book: Similarities

Each birthmark has own similarity calculation algorithm.
In other words, to compare $\mathcal{B}_f(p)$ and $\mathcal{B}_f(q)$ extracted from $f$ should use a certain method $\mathrm{sim}(\mathcal{B}_f(p), \mathcal{B}_f(q))$.
Besides, the range of $\mathrm{sim}(\mathcal{B}_f(p), \mathcal{B}_f(q))$ is $[0, 1]$,
Besides, the range of $\mathrm{sim}(\mathcal{B}_f(p), \mathcal{B}_f(q))$ is $[0, 1]$.
$0$ means two programs $p$ and $q$ are completely different, and $1$ means either is strongly suspects of copy from other.

Then, we introduce threshold $\varepsilon$ to show the suspecting rate.
We categorize resultant similarities into three groups below by $\varepsilon$.

\begin{eqnarray}\mathrm{sim}(\mathcal{B}_f(p), \mathcal{B}_f(q)) = \begin{cases}\geq \varepsilon      & \textit{copy relation}\\\\ \leq 1 - \varepsilon  & \textit{no copy relation}\\\\ \textit{otherwise}    & \textit{inconclusive}\end{cases}\end{eqnarray}

Note that, the typical value of $\varepsilon$ is 0.75.

{{< gototop >}}

## :closed_book: Theft Detection Process by Birthmarks

The goal of software birthmarks is detecting the suspects of copies, not prove the theft.
Therefore, the birthmark methods require to examine a huge amount of programs and to detect the suspects.

{{< image src="images/birthmark_system.svg" caption="Figure 1: theft detection process by the birthmarks" >}}

Figure 1 shows the process of the theft detection with the birthmarks.
The following descriptions shows above phases.

{{< label id="id1_phase" >}}
Note that, the users to execute the theft detection have the original (plaintiff) programs.
The user must collect the examine targets as defendant programs.
We cannot know when and where does software theft do.
Therefore, to improve the theft detection rate, we should collect programs as more as possible.
{{< /label >}}

{{< label id="id2_phase" >}}
  The next phase is the extraction phase for extracting birthmarks from the plaintiff and defendant programs with a given extraction method $f$.
  This phase generally requires a lot of time, because of extracting birthmarks from many programs.
{{< /label >}}

{{< label id="id3_phase" >}}
  In the comparison phase, we compare the plaintiff and defendant birthmarks by a round-robin, and obtain similarity list.
  The comparison count is the $m \times n$, $m$, and $n$ means the count of plaintiff and defendant birthmarks.
{{< /label >}}

{{< label id="id4_phase" >}}
  In the final phase in the birthmark system, we examine the pair of programs is theft or not from the similarity and both elements of birthmarks.
  Generally, this phase filters similarities by higher than EPSILON, then resultant pairs are the copy suspected programs.
{{< /label >}}

{{< label id="id5_phase" >}}
  After detecting the suspects by the birthmark systems, we must inspect the details of pairs to prove the theft.
  Because the birthmark methods bury no information, therefore, accidental matches may occur.
{{< /label >}}

{{< gototop >}}
