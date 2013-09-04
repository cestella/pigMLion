pigMLion
========

pigMLion is a set of machine learning and statistics utilities for the
Pig language on Hadoop.

This will take the form of a set of UDFs to facilitiate doing common
machine learning tasks on Hadoop.  Due to library dependencies, the
licensing of this library is GPLv2.

Feature Set
===========

What we have now

* Locality Sensitive Hashing UDF for spatial clustering
  * L1 and L2 metric LSH via Stable Distributions
  * Cosine Distance LSH via random hyperplanes

What we will soon have
 
* Sample Pig scripts for the LSH implementations
* Classifier UDFs for training as well as applications via Weka

FAQ
=====

* How does this differ from Mahout?
* How do I contribute?
