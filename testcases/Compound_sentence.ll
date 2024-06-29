; ModuleID = 'Compound_sentence.c'
source_filename = "Compound_sentence.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 4, ptr %2) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %3) #2
  store i32 5, ptr %2, align 4, !tbaa !3
  store i32 4, ptr %3, align 4, !tbaa !3
  br label %4

4:                                                ; preds = %29, %19, %0
  %5 = load i32, ptr %2, align 4, !tbaa !3
  %6 = icmp sgt i32 %5, 0
  br i1 %6, label %7, label %32

7:                                                ; preds = %4
  %8 = load i32, ptr %3, align 4, !tbaa !3
  %9 = icmp sgt i32 %8, 0
  br i1 %9, label %10, label %13

10:                                               ; preds = %7
  %11 = load i32, ptr %3, align 4, !tbaa !3
  %12 = add nsw i32 %11, -1
  store i32 %12, ptr %3, align 4, !tbaa !3
  br label %16

13:                                               ; preds = %7
  %14 = load i32, ptr %3, align 4, !tbaa !3
  %15 = add nsw i32 %14, 1
  store i32 %15, ptr %3, align 4, !tbaa !3
  br label %16

16:                                               ; preds = %13, %10
  %17 = load i32, ptr %3, align 4, !tbaa !3
  %18 = icmp eq i32 %17, 5
  br i1 %18, label %19, label %20

19:                                               ; preds = %16
  br label %4, !llvm.loop !7

20:                                               ; preds = %16
  %21 = load i32, ptr %2, align 4, !tbaa !3
  %22 = icmp sgt i32 %21, 0
  br i1 %22, label %23, label %24

23:                                               ; preds = %20
  br label %24

24:                                               ; preds = %23, %20
  br label %25

25:                                               ; preds = %24
  %26 = load i32, ptr %3, align 4, !tbaa !3
  %27 = icmp sgt i32 %26, 5
  br i1 %27, label %28, label %29

28:                                               ; preds = %25
  br label %29

29:                                               ; preds = %28, %25
  %30 = load i32, ptr %2, align 4, !tbaa !3
  %31 = add nsw i32 %30, -1
  store i32 %31, ptr %2, align 4, !tbaa !3
  br label %4, !llvm.loop !7

32:                                               ; preds = %4
  call void @llvm.lifetime.end.p0(i64 4, ptr %3) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %2) #2
  ret i32 0
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #1

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #1

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }
attributes #1 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #2 = { nounwind }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"frame-pointer", i32 2}
!2 = !{!"clang version 17.0.6"}
!3 = !{!4, !4, i64 0}
!4 = !{!"int", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
!7 = distinct !{!7, !8}
!8 = !{!"llvm.loop.mustprogress"}
