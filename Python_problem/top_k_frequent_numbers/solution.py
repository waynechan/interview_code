from collections import Counter


class Solution:
    def top_k_frequent(self, numbers, k):
        counts = Counter(numbers)

        # array with size = len(numbers) + 1
        buckets = [[] for _ in range(len(numbers) + 1)]

        for i, count in counts.items():
            buckets[count].append(i)

        result = []

        for i in reversed(range(len(buckets))):
            for j in range(len(buckets[i])):
                result.append(buckets[i][j])
                if len(result) == k:
                    return result

        return result


if __name__ == "__main__":
    result = Solution().top_k_frequent([1, 1, 5, 5, 5, 3], 2)
    print(result)
